/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.apache.jmeter.engine.event;

import org.apache.jmeter.testelement.TestElement;

/**
 * An iteration event provides information about the iteration number and the
 * source of the event.
 */
public class LoopIterationEvent {
	int iteration;

	TestElement source;

	public LoopIterationEvent(TestElement source, int iter) {
		iteration = iter;
		this.source = source;
	}

	/**
	 * Returns the iteration.
	 * 
	 * @return int
	 */
	public int getIteration() {
		return iteration;
	}

	/**
	 * Returns the source.
	 * 
	 * @return TestElement
	 */
	public TestElement getSource() {
		return source;
	}

	/**
	 * Sets the iteration.
	 * 
	 * @param iteration
	 *            The iteration to set
	 */
	public void setIteration(int iteration) {
		this.iteration = iteration;
	}
}