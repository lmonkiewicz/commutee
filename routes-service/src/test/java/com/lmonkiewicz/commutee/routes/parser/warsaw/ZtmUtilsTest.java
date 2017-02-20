package com.lmonkiewicz.commutee.routes.parser.warsaw;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by lmonkiewicz on 2017-02-20.
 */
public class ZtmUtilsTest {
    @Test
    public void recognizesSectionStartAtLineBegining() throws Exception {
        assertTrue(ZtmUtils.isSectionStart("*TY"));
    }

    @Test
    public void recognizesSectionStartAnywhereInLine() throws Exception {
        assertTrue(ZtmUtils.isSectionStart("      *TY"));
    }

    @Test
    public void recognizesSectionStartAnywhereInLineWithAdditionalData() throws Exception {
        assertTrue(ZtmUtils.isSectionStart("      *TY    2  3"));
    }

    @Test
    public void recognizesSectionEndAtLineBegining() throws Exception {
        assertTrue(ZtmUtils.isSectionEnd("#TY"));
    }

    @Test
    public void recognizesSectionEndAnywhereInLine() throws Exception {
        assertTrue(ZtmUtils.isSectionEnd("      #TY"));
    }

    @Test
    public void recognizesSectionEndAnywhereInLineWithAdditionalData() throws Exception {
        assertTrue(ZtmUtils.isSectionEnd("      #TY    2  3"));
    }

    @Test
    public void extractsSectionCodeFromTheBegining() throws Exception {
        assertEquals("TY", ZtmUtils.extractSectionCode("*TY"));
    }

    @Test
    public void extractsSectionCodeFromTheBeginingWithSectionEnd() throws Exception {
        assertEquals("TY", ZtmUtils.extractSectionCode("#TY"));
    }

    @Test
    public void extractsSectionCodeFromAnywhereInLine() throws Exception {
        assertEquals("TY", ZtmUtils.extractSectionCode("           *TY"));
    }

    @Test
    public void extractsSectionCodeFromAnywhereInLineWithAdditonalData() throws Exception {
        assertEquals("TY", ZtmUtils.extractSectionCode("           *TY   2   3"));
    }


    @Test
    public void splitIntoColumns() throws Exception {
        List<String> columns = ZtmUtils.asColumns("  123 567 89 12  ", 4, 4, 3, 3, 10, 5);

        assertEquals(6, columns.size());
        assertEquals("123", columns.get(0));
        assertEquals("567", columns.get(1));
        assertEquals("89", columns.get(2));
        assertEquals("12", columns.get(3));
        assertEquals("", columns.get(4));
        assertEquals("", columns.get(5));

    }
}