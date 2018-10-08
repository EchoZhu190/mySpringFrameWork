package com.josie.config;

/**
 * @Author：Josie Zhu
 * @Description：
 * @Date：Create in 16:49 2018/8/20
 */
public class PropertyValue {
        /**
         * 属性名称
         */
        private final String name;

        /**
         * 属性对象
         */
        private final Object value;

        /**
         * 构造器： 需要一个属性名称，一个属性对象
         */
        public PropertyValue(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        /**
         * 获取属性名称
         */
        public String getname() {
            return this.name;
        }

        /**
         * 获取属性对象
         */
        public Object getvalue() {
            return this.value;
        }
    }
