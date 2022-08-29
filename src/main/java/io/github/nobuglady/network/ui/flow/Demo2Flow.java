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
package io.github.nobuglady.network.ui.flow;

import org.springframework.stereotype.Component;

import io.github.nobuglady.network.fw.FlowRunner;
import io.github.nobuglady.network.fw.annotation.Node;

/**
 * 
 * @author NoBugLady
 *
 */
@Component
public class Demo2Flow extends FlowRunner {

	@Node(label = "start")
	public void processStart() throws InterruptedException {
		System.out.println("start start");
		Thread.sleep(3000);
		System.out.println("start end");
	}

	@Node(label = "search user")
	public void processSearchUser() throws InterruptedException {
		System.out.println("search user start (sleep 3 sec)");
		Thread.sleep(3000);
		System.out.println("search user end");
	}

	@Node(label = "search hotel")
	public void processSearchHotel() throws InterruptedException {
		System.out.println("search hotel start (sleep 3 sec)");
		Thread.sleep(3000);
		System.out.println("search hotel end");
	}

	@Node(label = "order")
	public void processOrder() throws InterruptedException {
		System.out.println("order start (sleep 3 sec)");
		Thread.sleep(3000);
		System.out.println("order end");
	}

	@Node(label = "order success")
	public void processSuccess() throws InterruptedException {
		System.out.println("order success start (sleep 3 sec)");
		Thread.sleep(3000);
		System.out.println("order success end");

	}
}