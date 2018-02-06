/*
 * Copyright 2006-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.consol.citrus.db.server.rules;

/**
 * This interface describe objects which create a mapping between
 * objects of type P and their appropriate rules, by evaluating
 * a predicate on P.
 *
 * @author Christoph Deppisch
 *
 */
public interface RuleMatcher<P> {

    /**
     * The implementation of a predicate on P
     * @param candidate The candidate to evaluate the predicate on
     * @return Whether the predicate was true or false
     */
    boolean match(P candidate);

    /**
     * Rule matcher that matches all candidates.
     * @return Always true
     */
    static <T> RuleMatcher<T> matchAll() {
        return (any) -> true;
    }
}
