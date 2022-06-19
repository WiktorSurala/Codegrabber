package de.surala.codegrabber;

import lombok.Data;

@Data
public class TwitterWrapper {
    private TwitterMessage[] data;
    private TwitterMeta meta;
}
