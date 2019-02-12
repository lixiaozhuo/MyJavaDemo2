module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost',
                pathRewrite: {
                    '^/api': ''
                },
                changeOrigin: true
            }
        }
    }
}
