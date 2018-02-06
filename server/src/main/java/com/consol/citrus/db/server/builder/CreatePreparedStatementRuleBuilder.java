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

package com.consol.citrus.db.server.builder;

import com.consol.citrus.db.server.controller.RuleBasedController;
import com.consol.citrus.db.server.rules.CreatePreparedStatementRule;
import com.consol.citrus.db.server.rules.RuleExecutor;
import com.consol.citrus.db.server.rules.RuleMatcher;

@SuppressWarnings({"unused", "WeakerAccess"})
public class CreatePreparedStatementRuleBuilder {

    private final RuleBasedController controller;
    private final RuleMatcher<String> ruleMatcher;

    public CreatePreparedStatementRuleBuilder(final RuleMatcher<String> ruleMatcher, final RuleBasedController controller) {
        this.controller = controller;
        this.ruleMatcher = ruleMatcher;
    }

    protected CreatePreparedStatementRule createRule(final RuleExecutor<String, Boolean> executor) {
        final CreatePreparedStatementRule createPreparedStatementRule =
                new CreatePreparedStatementRule(ruleMatcher, executor);
        controller.add(createPreparedStatementRule);
        return createPreparedStatementRule;
    }

    RuleMatcher<String> getRuleMatcher() {
        return ruleMatcher;
    }

    RuleBasedController getController() {
        return controller;
    }
}