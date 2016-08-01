package com.example.krunoslavtill.imageapplication.object.carriers;

import java.util.HashMap;

/**
 * Created by krunoslavtill on 22/07/16.
 */
public class Registry {


        volatile HashMap<String, Object> items = new HashMap<String, Object>();

        private static volatile Registry instance = null;

        protected Registry() {
            // Exists only to defeat instantiation.
        }

        public static Registry getInstance() {
            if (instance == null) {
                instance = new Registry();
            }
            return instance;
        }

        public void set(String key, Object value) {
            this.items.put(key, value);
        }

        public Object get(String key) {
            return this.items.get(key);
        }


}
