package net.renfei.security;

/**
 * 保密等级
 *
 * @author renfei
 */
public enum ConfidentialRankEnum {
    /**
     * 非密
     */
    UNCLASSIFIED(0, "非密"),
    /**
     * 秘密
     */
    CLASSIFIED(1, "秘密"),
    /**
     * 机密
     */
    CONFIDENTIAL(2, "机密");
    private final int rank;
    private final String text;

    ConfidentialRankEnum(int rank, String text) {
        this.rank = rank;
        this.text = text;
    }

    public static ConfidentialRankEnum parse(int rank) {
        switch (rank) {
            case 1:
                return CLASSIFIED;
            case 2:
                return CONFIDENTIAL;
            case 0:
            default:
                return UNCLASSIFIED;
        }
    }

    public static ConfidentialRankEnum parse(String text) {
        switch (text) {
            case "秘密":
                return CLASSIFIED;
            case "机密":
                return CONFIDENTIAL;
            case "非密":
            default:
                return UNCLASSIFIED;
        }
    }

    public int getRank() {
        return rank;
    }

    public String getText() {
        return text;
    }
}
