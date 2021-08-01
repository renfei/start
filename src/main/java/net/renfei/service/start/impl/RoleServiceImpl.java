package net.renfei.service.start.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.config.SystemConfig;
import net.renfei.exception.BusinessException;
import net.renfei.repository.dao.start.TStartRoleMapper;
import net.renfei.repository.dao.start.TStartRolePermissionMapper;
import net.renfei.repository.dao.start.model.TStartRole;
import net.renfei.repository.dao.start.model.TStartRoleExample;
import net.renfei.repository.dao.start.model.TStartRolePermission;
import net.renfei.repository.dao.start.model.TStartRolePermissionExample;
import net.renfei.sdk.entity.ListData;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.ListUtils;
import net.renfei.service.BaseService;
import net.renfei.service.start.PermissionService;
import net.renfei.service.start.RoleService;
import net.renfei.service.start.dto.PermissionDTO;
import net.renfei.service.start.dto.RoleDTO;
import net.renfei.service.start.type.ResourceTypeEnum;
import net.renfei.web.api.start.ao.RoleAO;
import net.renfei.web.api.start.vo.RoleDataVO;
import net.renfei.web.api.start.vo.RoleVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 系统角色服务
 *
 * @author renfei
 */
@Service
public class RoleServiceImpl extends BaseService implements RoleService {
    private final TStartRoleMapper roleMapper;
    private final PermissionService permissionService;
    private final TStartRolePermissionMapper rolePermissionMapper;

    protected RoleServiceImpl(SystemConfig systemConfig,
                              TStartRoleMapper roleMapper,
                              PermissionService permissionService,
                              TStartRolePermissionMapper rolePermissionMapper) {
        super(systemConfig);
        this.roleMapper = roleMapper;
        this.permissionService = permissionService;
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public RoleDataVO getAllRoleList(int pages, int rows) {
        TStartRoleExample example = new TStartRoleExample();
        example.createCriteria().andIsDeletedEqualTo(false);
        Page<TStartRole> page = PageHelper.startPage(pages, rows);
        roleMapper.selectByExample(example);
        // 查询所有权限
        List<PermissionDTO> allApiPermission = permissionService.getAllPermissionList(ResourceTypeEnum.API);
        List<PermissionDTO> allMenuPermission = permissionService.getAllPermissionList(ResourceTypeEnum.MENU);
        List<RoleDataVO.Permission> allApiPermissionList = new ArrayList<>(),
                allMenuPermissionList = new ArrayList<>();
        allApiPermission.forEach(permission -> {
            RoleDataVO.Permission permissionVo = new RoleDataVO.Permission();
            org.springframework.beans.BeanUtils.copyProperties(permission, permissionVo);
            allApiPermissionList.add(permissionVo);
        });
        allMenuPermission.forEach(permission -> {
            RoleDataVO.Permission permissionVo = new RoleDataVO.Permission();
            org.springframework.beans.BeanUtils.copyProperties(permission, permissionVo);
            allMenuPermissionList.add(permissionVo);
        });
        RoleDataVO roleDataVO = new RoleDataVO();
        roleDataVO.setAllApiPermission(allApiPermissionList);
        roleDataVO.setAllMenuPermission(allMenuPermissionList);
        List<RoleVO> startRoles = new CopyOnWriteArrayList<>();
        if (!BeanUtils.isEmpty(page.getResult())) {
            for (TStartRole startRole : page.getResult()
            ) {
                RoleDTO roleDTO = new RoleDTO();
                RoleVO roleVO = new RoleVO();
                org.springframework.beans.BeanUtils.copyProperties(startRole, roleVO);
                org.springframework.beans.BeanUtils.copyProperties(startRole, roleDTO);
                // 查询角色拥有的权限
                List<PermissionDTO> myApiPermission = permissionService.getPermissionListByRoleList(
                        new ArrayList<RoleDTO>() {{
                            this.add(roleDTO);
                        }}, ResourceTypeEnum.API);
                List<RoleDataVO.Permission> myApiPermissionList = new ArrayList<>();
                myApiPermission.forEach(permission -> {
                    RoleDataVO.Permission permissionVo = new RoleDataVO.Permission();
                    org.springframework.beans.BeanUtils.copyProperties(permission, permissionVo);
                    myApiPermissionList.add(permissionVo);
                });
                List<PermissionDTO> myMenuPermission = permissionService.getPermissionListByRoleList(
                        new ArrayList<RoleDTO>() {{
                            this.add(roleDTO);
                        }}, ResourceTypeEnum.MENU);
                List<RoleDataVO.Permission> myMenuPermissionList = new ArrayList<>();
                myMenuPermission.forEach(permission -> {
                    RoleDataVO.Permission permissionVo = new RoleDataVO.Permission();
                    org.springframework.beans.BeanUtils.copyProperties(permission, permissionVo);
                    myMenuPermissionList.add(permissionVo);
                });
                roleVO.setMyMenuPermission(myMenuPermissionList);
                roleVO.setMyApiPermission(myApiPermissionList);
                startRoles.add(roleVO);
            }
        }
        roleDataVO.setListData(new ListData<>(startRoles, page.getTotal(), page.getPageNum(), page.getPageSize(), page.getPages()));
        return roleDataVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editRoleById(RoleAO roleAO) {
        TStartRole startRole = new TStartRole();
        if (roleAO.getId() == null || roleAO.getId() == -1) {
            // 新增
            org.springframework.beans.BeanUtils.copyProperties(roleAO, startRole);
            startRole.setCreateTime(new Date());
            startRole.setIsDeleted(false);
            String uuid = UUID.randomUUID().toString().toLowerCase();
            startRole.setIsBuiltIn(false);
            startRole.setUuid(uuid);
            roleMapper.insertSelective(startRole);
            TStartRoleExample example = new TStartRoleExample();
            example.createCriteria().andUuidEqualTo(uuid);
            startRole = ListUtils.getOne(roleMapper.selectByExample(example));
        } else {
            // 修改
            startRole = roleMapper.selectByPrimaryKey(roleAO.getId());
            if (startRole == null) {
                throw new BusinessException("根据ID未找到资源信息，请查正。");
            }
            if (startRole.getIsBuiltIn()) {
                throw new BusinessException("系统内置角色，禁止编辑。");
            }
            org.springframework.beans.BeanUtils.copyProperties(roleAO, startRole);
            roleMapper.updateByPrimaryKey(startRole);
        }
        // 修改角色下面的权限，先软删除再添加
        TStartRolePermission deleteRolePermission = new TStartRolePermission();
        deleteRolePermission.setIsDeleted(true);
        TStartRolePermissionExample rolePermissionExample = new TStartRolePermissionExample();
        rolePermissionExample.createCriteria().andRoleUuidEqualTo(startRole.getUuid());
        rolePermissionMapper.updateByExampleSelective(deleteRolePermission, rolePermissionExample);
        for (String permissionUuid : roleAO.getPermissionUuid()
        ) {
            TStartRolePermission rolePermission = new TStartRolePermission();
            rolePermission.setRoleUuid(startRole.getUuid());
            rolePermission.setPermissionUuid(permissionUuid);
            rolePermission.setIsDeleted(false);
            rolePermission.setCreateTime(new Date());
            rolePermission.setUuid(UUID.randomUUID().toString().toLowerCase());
            rolePermissionMapper.insertSelective(rolePermission);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleById(Long id) {
        TStartRole startRole = roleMapper.selectByPrimaryKey(id);
        if (startRole == null) {
            throw new BusinessException("根据ID未找到资源信息，请查正。");
        }
        startRole.setIsDeleted(true);
        roleMapper.updateByPrimaryKey(startRole);
        TStartRolePermission deleteRolePermission = new TStartRolePermission();
        deleteRolePermission.setIsDeleted(true);
        TStartRolePermissionExample rolePermissionExample = new TStartRolePermissionExample();
        rolePermissionExample.createCriteria().andRoleUuidEqualTo(startRole.getUuid());
    }
}
