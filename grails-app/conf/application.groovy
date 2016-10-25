
grails.plugin.springsecurity.logout.postOnly = false

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'br.org.venturus.sorat.security.SecUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'br.org.venturus.sorat.security.SecUserSecRole'
grails.plugin.springsecurity.authority.className = 'br.org.venturus.sorat.security.SecRole'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/**',             access: ['IS_AUTHENTICATED_REMEMBERED']],
	[pattern: '/login/**',       access: ['permitAll']],
	[pattern: '/logout/**',      access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

grails.plugin.springsecurity.providerNames = ['ldapAuthProvider', 'anonymousAuthenticationProvider']
grails.plugin.springsecurity.ldap.context.managerDn = 'ManagerDN'
grails.plugin.springsecurity.ldap.context.managerPassword = 'ManagerPassword'
grails.plugin.springsecurity.ldap.context.server = 'ldap://server:389'
grails.plugin.springsecurity.ldap.search.base = 'dc=venturus,dc=local'
grails.plugin.springsecurity.ldap.search.filter = 'samAccountName={0}'
grails.plugin.springsecurity.ldap.authorities.groupSearchBase = 'OU=Venturus_VM,DC=venturus,DC=local'
grails.plugin.springsecurity.ldap.authorities.ignorePartialResultException = true
grails.plugin.springsecurity.ldap.mapper.userDetailsClass = 'SecUserDetails'

