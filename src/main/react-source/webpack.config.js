const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');

module.exports = {
    entry: './src/index.tsx',
    watch: true,
    mode: 'production',
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                loader: 'babel-loader'
            },
            {
                test:/\.(s*)css$/,
                use:['style-loader','css-loader', 'sass-loader']
            }
        ]
    },
    resolve: {
        extensions: [ '.tsx', '.ts', '.js' ]
    },
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, '../webapp')
    },
    optimization: {
         splitChunks: {
            chunks: 'all'
         }
    },
    plugins: [
        new HtmlWebpackPlugin({
            filename: 'index.html',
            title: 'Html2Test',
            template: 'public/main.html'
        })
    ]
};