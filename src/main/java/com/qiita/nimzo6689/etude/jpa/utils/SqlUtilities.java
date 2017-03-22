package com.qiita.nimzo6689.etude.jpa.utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SQL文生成用のユーティリティクラス
 *
 * @author nimzo6689
 */
public final class SqlUtilities {

    /**
     *
     * @throws IllegalArgumentException
     */
    private SqlUtilities() throws IllegalArgumentException {
        throw new IllegalArgumentException();
    }

    /**
     * SQLのIN句での抽出値としてバインドするList型オブジェクトを適切な文字列に展開する。（String型用） 例） List["apple",
     * "banana", "orange"] -> ("apple", "banana", "orange")
     *
     * 使用方法： List<String> nameList = Arrays.asList("tanaka", "sato", "suzuki");
     * "~ WHERE t.name IN ?"; pstmt.setParameter(1, nameList);
     *
     * @param list
     * @return
     */
    public static String makeInClauseByString(List<String> list) {
        return list.stream().collect(Collectors.joining("','", "('", "')"));
    }

    /**
     * SQLのIN句での抽出値としてバインドするList型オブジェクトを適切な文字列に展開する。（Number型用） 例） List[123, 456,
     * 789] -> (123, 456, 789)
     *
     * 使用方法： List<String> idList = Arrays.asList(123, 456, 789); "~ WHERE t.id
     * IN ?"; pstmt.setParameter(1, idList);
     *
     * @param list
     * @return
     */
    public static <T extends Number> String makeInClauseByNumber(List<T> list) {
        return list.stream().map(String::valueOf).collect(Collectors.joining(",", "(", ")"));
    }

}
