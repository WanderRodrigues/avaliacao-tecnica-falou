package com.falou.avaliacao_tecnica_falou.extensions

import com.falou.avaliacao_tecnica_falou.service.ResponseAny
import com.falou.avaliacao_tecnica_falou.service.ResponseEmpty
import com.falou.avaliacao_tecnica_falou.service.ResponseError
import com.falou.avaliacao_tecnica_falou.service.ResponseSuccess


fun <T> ResponseAny<T>.read(
    success: (T) -> Unit,
    error: ((Exception) -> Unit)? = null,
    empty: ((Int) -> Unit)? = null
) {
    when (this) {
        is ResponseSuccess -> success(this.body)
        is ResponseError -> error?.invoke(this.exception)
        is ResponseEmpty -> empty?.invoke(this.code)
    }
}