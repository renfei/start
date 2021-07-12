package net.renfei.web.api.start.ao;

import lombok.Data;
import net.renfei.security.ConfidentialRankEnum;

import java.io.Serializable;

/**
 * @author renfei
 */
@Data
public class UserAO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String uuid;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String lastName;
    private String firstName;
    private ConfidentialRankEnum confidentialRank;
}
