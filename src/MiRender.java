
import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class MiRender extends DefaultTableCellRenderer{
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelect,boolean hasFocus, int row,int column){
        
        if(row>0&&row<table.getRowCount()-1){
            this.setBackground(Color.green);
            //principal p= new principal();
            /*if(Integer.parseInt(p.NroSubImput)<row){
                this.setBackground(Color.blue);
            }*/
            //JOptionPane.showMessageDialog(this, p.NroSubImput);
        }
        else{
            this.setBackground(Color.ORANGE);
        }
        
        
        return super.getTableCellRendererComponent(table, value, isSelect, hasFocus, row, column);
    }
    
    
}
