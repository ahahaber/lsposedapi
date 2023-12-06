package io.github.libxpesed.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue

class XpesedIssueRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = emptyList()
}
