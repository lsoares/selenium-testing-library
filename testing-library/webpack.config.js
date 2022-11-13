module.exports = {
  entry: {
    "testing-library": './src/testing-library.js',
    "user-event": './src/user-event.js',
    "jest-dom": './src/jest-dom.js',
  },
  output: {
    filename: '[name].js',
    path: __dirname + '/dist',
  },
}
