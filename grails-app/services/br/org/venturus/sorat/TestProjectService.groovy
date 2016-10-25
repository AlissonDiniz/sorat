package br.org.venturus.sorat

import br.org.venturus.sorat.Project;
import br.org.venturus.helper.DomainHelper;
import br.org.venturus.helper.TestHelper;
import br.org.venturus.generator.GeneratorHelper;
import br.org.venturus.sorat.Domain;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class TestProjectService {
    
  private DomainHelper domainHelper;
  private TestHelper testHelper;
    
  def createTestProject(Project project, ZipOutputStream zip) {
    project.testFileList.each{projectTestFile ->
      zip.putNextEntry(new ZipEntry("project_test" + File.separator + projectTestFile.path + File.separator + projectTestFile.name));
      zip.write(projectTestFile.content.bytes);
    }
    
    this.domainHelper = new DomainHelper();
    this.testHelper = new TestHelper();
    this.createMenuFile(project, zip);
    project.requirementList.each{requirement ->
      def domain = requirement.domain;
      if(domain){
        this.domainHelper.createFile(project, requirement, zip);
        this.testHelper.createFile(project, requirement, zip);
      }
    };
  }
    
  def createPartialTestProject(Requirement requirement, ZipOutputStream zip) {
    this.domainHelper = new DomainHelper();
    this.testHelper = new TestHelper();
    def domain = requirement.domain;
    if(domain){
      this.domainHelper.createFile(requirement, zip);
      this.testHelper.createFile(requirement, zip);
    }
  }
  
  private void createMenuFile(Project project, ZipOutputStream zip){
    StringBuilder builder = new StringBuilder();
    zip.putNextEntry(new ZipEntry("project_test"+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"br"+File.separator+"org"+File.separator+"venturus"+File.separator+"qa"+File.separator+"auto"+File.separator+"ui"+File.separator+"Menu.java"));
    builder.append("package br.org.venturus.qa.auto.ui;");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("public class Menu {");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("    public interface Option {");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("        String getId();");
    builder.append(System.getProperty("line.separator"));
    builder.append("        String getPath();");
    builder.append(System.getProperty("line.separator"));
    builder.append("        Option getParent();");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("    }");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("    public enum Main implements Option {");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append('        MENU("command-menu", null, null),');
    builder.append(System.getProperty("line.separator"));
        
    project.requirementList.each{requirement ->
      def domain = requirement.domain;
      if(domain){
        builder.append("        ").append(GeneratorHelper.getDomainEnum(domain.name)).append('("').append(domain.elementId).append('", "').append(domain.path).append('", Main.MENU),');
        builder.append(System.getProperty("line.separator"));
      }
    };
        
    builder.append('        HOME("menu-home", "/", Main.MENU);');
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("        private final String id;");
    builder.append(System.getProperty("line.separator"));
    builder.append("        private final String path;");
    builder.append(System.getProperty("line.separator"));
    builder.append("        private final Option parent;");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("        private Main(String id, String path, Option parent) {");
    builder.append(System.getProperty("line.separator"));
    builder.append("            this.id = id;");
    builder.append(System.getProperty("line.separator"));
    builder.append("            this.path = path;");
    builder.append(System.getProperty("line.separator"));
    builder.append("            this.parent = parent;");
    builder.append(System.getProperty("line.separator"));
    builder.append("        }");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("        @Override");
    builder.append(System.getProperty("line.separator"));
    builder.append("        public String getId() {");
    builder.append(System.getProperty("line.separator"));
    builder.append("            return id;");
    builder.append(System.getProperty("line.separator"));
    builder.append("        }");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("        @Override");
    builder.append(System.getProperty("line.separator"));
    builder.append("        public String getPath() {");
    builder.append(System.getProperty("line.separator"));
    builder.append("            return path;");
    builder.append(System.getProperty("line.separator"));
    builder.append("        }");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("        @Override");
    builder.append(System.getProperty("line.separator"));
    builder.append("        public Option getParent() {");
    builder.append(System.getProperty("line.separator"));
    builder.append("            return parent;");
    builder.append(System.getProperty("line.separator"));
    builder.append("        }");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("    }");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("    "+File.separator+"*");
    builder.append(System.getProperty("line.separator"));
    builder.append("        Sample Menu + SubMenu");
    builder.append(System.getProperty("line.separator"));
    builder.append("    *"+File.separator+"");
    builder.append(System.getProperty("line.separator"));
    builder.append("    public enum Security implements Option {");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append('        INDEX("menu-security", null, Main.MENU),');
    builder.append(System.getProperty("line.separator"));
    builder.append('        PROFILES("submenu-security-profiles", "security/profile", Menu.Security.INDEX),');
    builder.append(System.getProperty("line.separator"));
    builder.append('        USERS("submenu-security-users", "security/user", Menu.Security.INDEX);');
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("        private final String id;");
    builder.append(System.getProperty("line.separator"));
    builder.append("        private final String path;");
    builder.append(System.getProperty("line.separator"));
    builder.append("        private final Option parent;");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("        private Security(String id, String path, Option parent) {");
    builder.append(System.getProperty("line.separator"));
    builder.append("            this.id = id;");
    builder.append(System.getProperty("line.separator"));
    builder.append("            this.path = path;");
    builder.append(System.getProperty("line.separator"));
    builder.append("            this.parent = parent;");
    builder.append(System.getProperty("line.separator"));
    builder.append("        }");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("        @Override");
    builder.append(System.getProperty("line.separator"));
    builder.append("        public String getId() {");
    builder.append(System.getProperty("line.separator"));
    builder.append("            return id;");
    builder.append(System.getProperty("line.separator"));
    builder.append("        }");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("        @Override");
    builder.append(System.getProperty("line.separator"));
    builder.append("        public String getPath() {");
    builder.append(System.getProperty("line.separator"));
    builder.append("            return path;");
    builder.append(System.getProperty("line.separator"));
    builder.append("        }");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("        @Override");
    builder.append(System.getProperty("line.separator"));
    builder.append("        public Option getParent() {");
    builder.append(System.getProperty("line.separator"));
    builder.append("            return parent;");
    builder.append(System.getProperty("line.separator"));
    builder.append("        }");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("    }");
    builder.append(System.getProperty("line.separator"));
    builder.append(System.getProperty("line.separator"));
    builder.append("}");
    zip.write(builder.toString().bytes);
  }
}
