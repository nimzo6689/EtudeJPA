package com.qiita.nimzo6689.etude.jpa.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author nimzo6689
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum AuthorityType {

    ADMIN("0", "管理者"),
    NORMAL("1", "一般"),
    GUEST("2", "ゲスト"),
    FORBIDDEN("8", "アクセス禁止"),
    UNKNOWN("9", "不明"),;

    private final String value;
    private final String description;

    /**
     *
     * @param value
     * @return
     */
    public static AuthorityType of(String value) {
        for (AuthorityType authorityType : AuthorityType.values()) {
            if (authorityType.value.equals(value)) {
                return authorityType;
            }
        }
        throw new IllegalArgumentException(
                "Type:" + value + " is not a valid " + AuthorityType.class.getName() + " value.");
    }

}
