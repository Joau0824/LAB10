package pe.edu.pucp.iweb.lab10.DTO;

public class EmpleadosPorRegionDTO {

    public class EmpleadosPorRegionDto {

        private String nombreRegion;
        private int cantidadEmpleados;

        public String getNombreRegion() {
            return nombreRegion;
        }

        public void setNombreRegion(String nombreRegion) {
            this.nombreRegion = nombreRegion;
        }

        public int getCantidadEmpleados() {
            return cantidadEmpleados;
        }

        public void setCantidadEmpleados(int cantidadEmpleados) {
            this.cantidadEmpleados = cantidadEmpleados;
        }
    }

}
