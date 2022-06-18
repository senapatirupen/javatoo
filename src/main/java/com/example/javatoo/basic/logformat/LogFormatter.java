package com.example.javatoo.basic.logformat;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class LogFormatter {
    private static final String LOG_FORMAT = "status:{}, message:{}, IqId: {}, time stamp: {}, developer message: {}";
    private static final String LOG_FORMAT_C = "time stamp: {}, status:{}, IqId: {}, message:{}, error: {}";

    public static void main(String[] args) {
        if (5 < 6) {
            log.info(LOG_FORMAT, 201, "error.getMessage()", 123466987, new Date(), "adl data create for new proto");
            // you may neglect last entries, which will add blank value to the entry
            log.info(LOG_FORMAT, 404, "error.getMessage()", 123466987, new Date());
            // you may add blank string "" or null as the value if not applicable
            log.info(LOG_FORMAT, "", "error.getMessage()", 123466987, new Date());
            //
            log.info(LOG_FORMAT_C, new Date(), 201, 123466987, "proto record created", "error.getMessage()");
        }
    }

}
