import {queryAllByRole, queryAllByLabelText, queryAllByPlaceholderText, queryAllByText, queryAllByDisplayValue, queryAllByAltText, queryAllByTitle, queryAllByTestId, 
        fireEvent} from '@testing-library/dom'

window.__TL__ = {
    queryAllByRole,
    queryAllByLabelText,
    queryAllByPlaceholderText,
    queryAllByText,
    queryAllByDisplayValue,
    queryAllByAltText,
    queryAllByTitle,
    queryAllByTestId,
    fireEvent
}

if (/Firefox/.test(navigator.userAgent)) {
    window.__TL__ = {
        ...window.__TL__,
        queryAllByRole            : (container, role,  options) => queryAllByRole            (container, role, wrapRoleOptions(options)),
        queryAllByLabelText       : (container, text,  options) => queryAllByLabelText       (container, wrapTextMatch(text), wrapTextOptions(options)),
        queryAllByPlaceholderText : (container, text,  options) => queryAllByPlaceholderText (container, wrapTextMatch(text), wrapTextOptions(options)),
        queryAllByText            : (container, text,  options) => queryAllByText            (container, wrapTextMatch(text), wrapTextOptions(options)),
        queryAllByDisplayValue    : (container, value, options) => queryAllByDisplayValue    (container, wrapTextMatch(value), wrapTextOptions(options)),
        queryAllByAltText         : (container, text,  options) => queryAllByAltText         (container, wrapTextMatch(text), wrapTextOptions(options)),
        queryAllByTitle           : (container, title, options) => queryAllByTitle           (container, wrapTextMatch(title), wrapTextOptions(options)),
        queryAllByTestId          : (container, text,  options) => queryAllByTestId          (container, wrapTextMatch(text), wrapTextOptions(options)),
    }
    function wrapRoleOptions(options) {
        return options && { ...options,
            name: wrapTextMatch(options.name),
            description: wrapTextMatch(options.description),
            value: options.value && { ...options.value, text: wrapTextMatch(options.value.text) }
        }
    }
    function wrapTextOptions(options) {
        return options && { ...options, normalizer: wrapFunction(options.normalizer) }
    }
    function wrapTextMatch(maybeRegexOrFunc) {
        if (Object.prototype.toString.call(maybeRegexOrFunc) === "[object RegExp]") {
            return new RegExp(maybeRegexOrFunc.source, maybeRegexOrFunc.flags)
        }
        return wrapFunction(maybeRegexOrFunc)
    }
    function wrapFunction(maybeFunc) {
        if (Object.prototype.toString.call(maybeFunc) === "[object Function]") {
            return (content, element) => maybeFunc.call(null, content, element)
        }
        return maybeFunc
    }
}
