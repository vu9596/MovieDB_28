package vunt.com.vn.moviedb_28.util;

public class StringUtils {
    public static String append(String... strings) {
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string);
        }
        return builder.toString();
    }

    public static String getImageLink(int size, String url) {
        return StringUtils.append(Constant.IMAGE_LINK, String.valueOf(size), Constant.SLASH, url);
    }
}
