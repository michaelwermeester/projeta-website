/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojeta;

/**
 *
 * @author michael
 */
import java.io.Serializable;

public class ProjectSimple implements Serializable {

        private String name;
        
        private String size;
        
        private String type;
        
        private String status;
        
        private int id;
        
        public ProjectSimple(String name, String size, String type, String status) {
                this.name = name;
                this.size = size;
                this.type = type;
                this.status = status;
        }
        
        public ProjectSimple(int id, String name, String size, String type, String status) {
                
                this.id = id;
                this.name = name;
                this.size = size;
                this.type = type;
                this.status = status;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getSize() {
                return size;
        }

        public void setSize(String size) {
                this.size = size;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }
        
        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        @Override
        public String toString() {
                return name;
        }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
