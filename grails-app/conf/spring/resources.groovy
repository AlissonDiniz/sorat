import br.org.venturus.security.SecUserDetailsContextMapper;

beans = {
    ldapUserDetailsMapper(SecUserDetailsContextMapper) {
        dataSource = ref("dataSource");
    }
}