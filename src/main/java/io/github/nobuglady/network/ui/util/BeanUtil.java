/*
 * Copyright (c) 2021-present, NoBugLady Contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */
package io.github.nobuglady.network.ui.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author NoBugLady
 *
 */
public class BeanUtil {

	/**
	 * 
	 * @param <T>
	 * @param srcList
	 * @param cls
	 * @return
	 */
	public static <T> List<T> copyList(List<?> srcList, Class<T> cls) {
		List<T> targetList = new ArrayList<T>();

		if (srcList != null) {
			for (Object src : srcList) {
				T target = copy(src, cls);
				targetList.add(target);
			}
		}

		return targetList;
	}

	/**
	 * 
	 * @param <T>
	 * @param src
	 * @param cls
	 * @return
	 */
	public static <T> T copy(Object src, Class<T> cls) {
		if (src == null) {
			return null;
		}

		try {
			T target = cls.getDeclaredConstructor().newInstance();

			Map<String, Object> fieldNameUpperValueMap = new HashMap<String, Object>();

			Method[] methodsSrc = src.getClass().getMethods();
			if (methodsSrc != null) {
				for (Method method : methodsSrc) {
					if (method.getName().startsWith("get")) {
						String filedNameUpper = method.getName().substring(3);
						fieldNameUpperValueMap.put(filedNameUpper, method.invoke(src));
					}
				}
			}

			Method[] methodsTarget = target.getClass().getMethods();
			if (methodsTarget != null) {
				for (Method method : methodsTarget) {
					if (method.getName().startsWith("set")) {
						String filedNameUpper = method.getName().substring(3);
						method.invoke(target, fieldNameUpperValueMap.get(filedNameUpper));
					}
				}
			}

			return target;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
}