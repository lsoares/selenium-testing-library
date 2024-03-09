import {
  queryAllByRole,
  queryAllByLabelText,
  queryAllByPlaceholderText,
  queryAllByText,
  queryAllByDisplayValue,
  queryAllByAltText,
  queryAllByTitle,
  queryAllByTestId,
  fireEvent,
} from "@testing-library/dom";

window.__TL__ = {
  queryAllByRole,
  queryAllByLabelText,
  queryAllByPlaceholderText,
  queryAllByText,
  queryAllByDisplayValue,
  queryAllByAltText,
  queryAllByTitle,
  queryAllByTestId,
};

if (/Firefox/.test(navigator.userAgent)) {
  window.__TL__ = Object.fromEntries(
    Object.entries(window.__TL__)
      .map(([key, originalFunction]) => [
        key,
        (container, text, options) =>
          originalFunction(
            container,
            wrapTextMatch(text),
            wrapOptions(options)
          ),
      ])
  );

  function wrapOptions(options) {
    return (
      options && {
        ...options,
        name: wrapTextMatch(options.name),
        description: wrapTextMatch(options.description),
        normalizer: wrapFunction(options.normalizer),
        value: options.value && {
          ...options.value,
          text: wrapTextMatch(options.value.text),
        },
      }
    );
  }

  function wrapTextMatch (maybeRegexOrFunc) {
    return Object.prototype.toString.call(maybeRegexOrFunc) === "[object RegExp]"
      ? new RegExp(maybeRegexOrFunc.source, maybeRegexOrFunc.flags)
      : wrapFunction(maybeRegexOrFunc);
  }

  function wrapFunction (maybeFunc) {
    return Object.prototype.toString.call(maybeFunc) === "[object Function]"
      ? (content, element) => maybeFunc.call(null, content, element)
      : maybeFunc;
    }
}

window.__TL__.fireEvent = fireEvent;