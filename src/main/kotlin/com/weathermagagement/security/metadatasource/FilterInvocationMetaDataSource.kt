import org.springframework.security.access.ConfigAttribute
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource
import org.springframework.security.web.util.matcher.RequestMatcher
import java.util.HashSet
import javax.servlet.http.HttpServletRequest

class FilterInvocationMetaDataSource: FilterInvocationSecurityMetadataSource{

    val requestMap: LinkedHashMap<RequestMatcher, List<ConfigAttribute>> = LinkedHashMap()

    override fun getAttributes(`object`: Any?): List<ConfigAttribute>? {
        val request: HttpServletRequest = (`object` as FilterInvocation).request
        if (request != null) {
            for (entry in requestMap) {
                val matcher: RequestMatcher = entry.key
                if (matcher.matches(request)) {
                    return entry.value
                }
            }
        }
        return null
    }

    override fun getAllConfigAttributes(): MutableCollection<ConfigAttribute> {
        val attributes: MutableSet<ConfigAttribute> = HashSet()
        this.requestMap.values.forEach(attributes::addAll)
        return attributes
    }

    override fun supports(clazz: Class<*>?): Boolean {
        return FilterInvocation::class.java.isAssignableFrom(clazz)
    }
}