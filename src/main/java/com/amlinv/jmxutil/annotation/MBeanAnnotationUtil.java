/**
  * Copyright 2015 AML Innovation & Consulting LLC
  *
  * Licensed to the Apache Software Foundation (ASF) under one or more
  * contributor license agreements.  See the NOTICE file distributed with
  * this work for additional information regarding copyright ownership.
  * The ASF licenses this file to You under the Apache License, Version 2.0
  * (the "License"); you may not use this file except in compliance with
  * the License.  You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */

package com.amlinv.jmxutil.annotation;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by art on 3/31/15.
 */
public class MBeanAnnotationUtil {
    public static String getLocationONamePattern (Object mbeanLocation) {
        MBeanLocation location = mbeanLocation.getClass().getAnnotation(MBeanLocation.class);

        if ( location == null ) {
            return  null;
        }

        return location.onamePattern();
    }

    public static Map<String, Method> getAttributes (Object mbeanLocation) {
        Map<String, Method> result = new TreeMap<String, Method>();

        Method[] methods = mbeanLocation.getClass().getMethods();

        for ( Method oneMethod : methods ) {
            MBeanAttribute attribute = oneMethod.getAnnotation(MBeanAttribute.class);

            if ( attribute != null ) {
                //
                // Record the name of the attribute with the method, which must be the setter.
                //
                result.put(attribute.name(), oneMethod);
            }
        }

        return  result;
    }
}