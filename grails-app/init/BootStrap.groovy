
import br.org.venturus.sorat.security.SecUser;
import br.org.venturus.sorat.security.SecRole;
import br.org.venturus.sorat.security.SecUserSecRole;

class BootStrap {

  def springSecurityService

  def init = { servletContext ->
    def adminRole = SecRole.findByAuthority('ROLE_ADMIN') ?: new SecRole(authority: 'ROLE_ADMIN', description: 'Administrator Profile').save(failOnError: true);
    def managerRole = SecRole.findByAuthority('ROLE_MANAGER') ?: new SecRole(authority: 'ROLE_MANAGER', description: 'Manager Profile').save(failOnError: true);
    def developerRole = SecRole.findByAuthority('ROLE_DEVELOPER') ?: new SecRole(authority: 'ROLE_DEVELOPER', description: 'Developer Profile').save(failOnError: true);
    def testerRole = SecRole.findByAuthority('ROLE_TESTER') ?: new SecRole(authority: 'ROLE_TESTER', description: 'Tester Profile').save(failOnError: true);
    
    def adminUser = SecUser.findByUsername('big.brother') ?: new SecUser(
      name: 'Administrator',
      username: 'big.brother',
      password: 'sorat',
      userCreated: 'system',
      dateCreated: new Date(),
      lastUpdated: new Date(),
      enabled: true).save(failOnError: true);

    if (!adminUser.authorities.contains(adminRole)) {
      SecUserSecRole.create adminUser, adminRole
    }
  }
  def destroy = {
  }
}
