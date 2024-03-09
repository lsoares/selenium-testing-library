module.exports = {
  mode: "production",
  entry: {
    "testing-library": './src/testing-library.js',
    "user-event": './src/user-event.js',
  },
  output: {
    filename: '[name].js',
    path: __dirname + '/dist',
  },
}
