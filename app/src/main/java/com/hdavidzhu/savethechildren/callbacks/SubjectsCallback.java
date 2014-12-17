package com.hdavidzhu.savethechildren.callbacks;

import java.util.List;

//Allows the Singleton and the Class to run async
public interface SubjectsCallback {
    //handle connects the VolleySingleton and Subject so they have consistant syntax
    public void handle(List<String> subjects);
}