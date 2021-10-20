package software.ensemble.ensemble_light_kt.integrations.api_client.helpers

/**
 * Common class with HTTP(s) methods
 */
class HttpMethod(val value: String) {
    companion object {
        var GET = "GET"
        var POST = "POST"
        var PUT = "PUT"
        var DELETE = "DELETE"
        var PATCH = "PATCH"
        var HEAD = "HEAD"
        var OPTIONS = "OPTIONS"
    }
}