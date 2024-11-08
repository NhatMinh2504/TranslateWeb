package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a vocabulary entry with fields for English and Vietnamese words,
 * type, status, and the date it was added or modified.
 */
public class Vocabulary {
    private int id;
    private String englishWord;
    private String vietnameseWord;
    private String type;
    private String status;
    private Date date;

    // Default constructor
    public Vocabulary() {
    }

    // Constructor with all fields
    public Vocabulary(int id, String englishWord, String vietnameseWord, String type, String status, Date date) {
        this.id = id;
        this.englishWord = englishWord;
        this.vietnameseWord = vietnameseWord;
        this.type = type;
        this.status = status;
        this.date = date;
    }

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for englishWord
    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    // Getter and Setter for vietnameseWord
    public String getVietnameseWord() {
        return vietnameseWord;
    }

    public void setVietnameseWord(String vietnameseWord) {
        this.vietnameseWord = vietnameseWord;
    }

    // Getter and Setter for type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter for date
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Optional: Method to get a formatted date string
    public String getFormattedDate() {
        if (date == null) {
            return "N/A";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}
