package net.renfei.repository.manager.ip2location;

// package-private access
class MetaData {
    private int _BaseAddr = 0;
    private int _DBCount = 0;
    private int _DBColumn = 0;
    private int _DBType = 0;
    private int _DBDay = 1;
    private int _DBMonth = 1;
    private int _DBYear = 1;
    private int _BaseAddrIPv6 = 0;
    private int _DBCountIPv6 = 0;
    private boolean _OldBIN = false;
    private boolean _Indexed = false;
    private boolean _IndexedIPv6 = false;
    private int _IndexBaseAddr = 0;
    private int _IndexBaseAddrIPv6 = 0;

    MetaData() {

    }

    // all package-private access
    int getBaseAddr() {
        return _BaseAddr;
    }

    void setBaseAddr(int value) {
        _BaseAddr = value;
    }

    int getDBCount() {
        return _DBCount;
    }

    void setDBCount(int value) {
        _DBCount = value;
    }

    int getDBColumn() {
        return _DBColumn;
    }

    void setDBColumn(int value) {
        _DBColumn = value;
    }

    int getDBType() {
        return _DBType;
    }

    void setDBType(int value) {
        _DBType = value;
    }

    int getDBDay() {
        return _DBDay;
    }

    void setDBDay(int value) {
        _DBDay = value;
    }

    int getDBMonth() {
        return _DBMonth;
    }

    void setDBMonth(int value) {
        _DBMonth = value;
    }

    int getDBYear() {
        return _DBYear;
    }

    void setDBYear(int value) {
        _DBYear = value;
    }

    int getBaseAddrIPv6() {
        return _BaseAddrIPv6;
    }

    void setBaseAddrIPv6(int value) {
        _BaseAddrIPv6 = value;
    }

    int getDBCountIPv6() {
        return _DBCountIPv6;
    }

    void setDBCountIPv6(int value) {
        _DBCountIPv6 = value;
    }

    boolean getOldBIN() {
        return _OldBIN;
    }

    void setOldBIN(boolean value) {
        _OldBIN = value;
    }

    boolean getIndexed() {
        return _Indexed;
    }

    void setIndexed(boolean value) {
        _Indexed = value;
    }

    boolean getIndexedIPv6() {
        return _IndexedIPv6;
    }

    void setIndexedIPv6(boolean value) {
        _IndexedIPv6 = value;
    }

    int getIndexBaseAddr() {
        return _IndexBaseAddr;
    }

    void setIndexBaseAddr(int value) {
        _IndexBaseAddr = value;
    }

    int getIndexBaseAddrIPv6() {
        return _IndexBaseAddrIPv6;
    }

    void setIndexBaseAddrIPv6(int value) {
        _IndexBaseAddrIPv6 = value;
    }
}
