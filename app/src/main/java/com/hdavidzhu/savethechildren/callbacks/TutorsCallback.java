package com.hdavidzhu.savethechildren.callbacks;

import java.util.List;

public interface TutorsCallback {
    public void handle(List<String> tutors) throws InterruptedException;
}
