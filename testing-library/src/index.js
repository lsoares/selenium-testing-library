/**
    copied from https://github.com/testing-library/testing-playground/blob/develop/devtools/src/window/testing-library.js
    credits to the author
**/

import {
  screen,
  within,
  getSuggestedQuery,
  fireEvent,
  getRoles,
} from '@testing-library/dom'

window.__TESTING_PLAYGROUND__ = window.__TESTING_PLAYGROUND__ || {}

function augmentQuery(query) {
  return (...args) => {
    const result = query(...args)
    return result
  }
}

for (const prop of Object.keys(screen)) {
    window.screen[prop] = window.screen[prop] || augmentQuery(screen[prop])
    window[prop] = window.screen[prop]
}

window.getRoles = getRoles
window.fireEvent = fireEvent
window.getSuggestedQuery = getSuggestedQuery
window.within = within
window.container = window.document.body
