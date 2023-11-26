package org.example.interfaces;

import com.google.inject.ImplementedBy;
import org.example.models.scene.Story;

@ImplementedBy(Story.class)
public interface Tellable {
    public void tell();
}

