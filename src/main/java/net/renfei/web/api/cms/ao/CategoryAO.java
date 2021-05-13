package net.renfei.web.api.cms.ao;

import lombok.Data;
import net.renfei.security.ConfidentialRankEnum;

import java.io.Serializable;

/**
 * @author renfei
 */
@Data
public class CategoryAO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String enName;
    private String zhName;
    private String featuredImage;
    private ConfidentialRankEnum confidentialRank;
}
