package com.mygreendao.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by TR 105 on 2017/10/11.
 */
@Entity
public class book {

    private int bookId;

    private String bookName;

    @Generated(hash = 1747355784)
    public book(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    @Generated(hash = 1626308135)
    public book() {
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }


}
