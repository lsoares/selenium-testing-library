import {screen, fireEvent} from '@testing-library/dom'

window.screen = screen
window.fireEvent = fireEvent

window.__TL__Function = Function
window.__TL__RegExp = RegExp