package com.lmonkiewicz.commutee.routes.parser.warsaw;

import com.google.common.collect.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by lmonkiewicz on 2017-02-19.
 */
public class ZtmUtils {

    private static final String SECTION_START = "*";
    private static final String SECTION_END = "#";
    public static final int DEFAULT_INDENT_SIZE = 3;
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static boolean isSectionStart(String line){
        return line.trim().startsWith(SECTION_START);
    }

    public static boolean isSectionEnd(String line){
        return line.trim().startsWith(SECTION_END);
    }

    /**
     * Section name consists of * or # character, and 2 letter section code, ex: *TY or #TY
     *
     * @param line input line
     * @return section code
     * @throws SectionReaderException when line is not in correct format
     */
    public static String extractSectionCode(@NotNull String line) throws SectionReaderException {
        line = line.trim();
        if (line.length() < 3) {
            throw new SectionReaderException("Bad section name format '" + line + "'");
        }
        return line.substring(1, 3);
    }

    public static int getIndentationLevel(@NotNull String line, int levelSize){
        int spaces = 0;
        for (int i = 0; i < line.length(); i++) {
            if (Character.isSpaceChar(line.charAt(i))){
                spaces++;
            }
            else {
                break;
            }
        }
        return spaces / levelSize;
    }

    /**
     * Splits input String line into list according to defined columns sizes
     *
     * @param line input String
     * @param columnsSizes column widths definition
     *
     * @return
     */
    public static List<String> asColumns(int indent, @NotNull String line, int... columnsSizes){
        final List<String> result = Lists.newArrayList();

        String input = trimStart(indent, line);
        for (int columnSize: columnsSizes){
            String value = "";
            if (input.length() >= columnSize) {
                value = input.substring(0, columnSize).trim();
                input = input.substring(columnSize);
            }
            else {
                value = input.substring(0).trim();
                input = "";
            }
            result.add(value);
        }
        return result;
    }

    @NotNull
    public static String trimStart(int indent, @NotNull String line){
        int idx = indent * DEFAULT_INDENT_SIZE;
        if (line.length() > idx){
            return line.substring(idx);
        }
        return "";
    }

    @NotNull
    public static String trimTrailingString(@NotNull String input, @NotNull String ending) {
        int index = input.lastIndexOf(ending);
        if (index >= 0){
            return input.substring(0, index);
        }
        return input;
    }
}
