package vn.huynh.tikitest.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by duong on 3/29/2019.
 *
 */

public class FormatDataUtilsTest {
    @Test
    public void formatKeywordCorrect() {
        assertEquals("Con co ma\ndi dem", FormatDataUtils.formatKeyword("Con co ma di dem"));
        assertEquals("Dac nhan\ntam", FormatDataUtils.formatKeyword("Dac nhan tam"));
        assertEquals("Lan\nAnh", FormatDataUtils.formatKeyword("Lan Anh"));
        assertEquals("Harry\nPotter", FormatDataUtils.formatKeyword("  Harry Potter"));
        assertEquals("Nam lay banh\ncua Thu", FormatDataUtils.formatKeyword("Nam lay banh cua Thu"));
        assertEquals("Nguyen\nNhat Anh", FormatDataUtils.formatKeyword("Nguyen Nhat  Anh"));
        assertEquals("Xiaomi", FormatDataUtils.formatKeyword("Xiaomi"));
        assertEquals("", FormatDataUtils.formatKeyword(" "));
        assertEquals("anh chính là\nthanh xuân của em", FormatDataUtils.formatKeyword("anh chính là thanh xuân của em"));
    }
}
