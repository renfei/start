package net.renfei.web.api.cms.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * @author renfei
 */
@Data
public class TagAO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String enName;
    private String zhName;
    private String describe;
}
