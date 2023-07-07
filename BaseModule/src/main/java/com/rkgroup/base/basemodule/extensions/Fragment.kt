package com.rkgroup.base.basemodule.extensions

import androidx.fragment.app.Fragment

val Fragment.preferences get() = requireContext().preferences
val Fragment.resourcesManager get() = requireContext().resourcesManager
val Fragment.workManager
    get() = requireContext().workManager