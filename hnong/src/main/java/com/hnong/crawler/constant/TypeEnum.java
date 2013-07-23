package com.hnong.crawler.constant;

/**
 * User: chris
 * Date: 13-7-14
 */
public enum TypeEnum {
    NONE(0),
    NEWS(1),
    MARKET(2),
    TEC(3);

    private int type;

    private TypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public static TypeEnum getBusinessByType(int type) {
        for (TypeEnum consumer : TypeEnum.values()) {
            if (type == (consumer.getType())) {
                return consumer;
            }
        }
        return TypeEnum.NONE;
    }

    public static TypeEnum getBusinessByType(String type) {
        for (TypeEnum consumer : TypeEnum.values()) {
            if (consumer.name().equals(type)) {
                return consumer;
            }
        }
        return TypeEnum.NONE;
    }

}
