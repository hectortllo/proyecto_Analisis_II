/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.clases;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import gt.edu.url.entity.Venta;

/**
 *
 * @author Héctor Tello <hectortllo@gmail.com>
 */
public class ProxyTblVentas implements TableModel {

    DefaultTableModel realSubject = new DefaultTableModel();

    public ProxyTblVentas(List<Venta> ventas) {
        realSubject.addColumn("No.");
        realSubject.addColumn("Total");
        realSubject.addColumn("Fecha");
        realSubject.addColumn("Cliente");
        realSubject.addColumn("Usuario");
        Object[] row = new Object[5];
        for (Venta venta : ventas) {
            row[0] = venta.getId();
            row[1] = venta.getTotal();
            row[2] = venta.getFecha();
            row[3] = venta.getClienteId().getNombre();
            row[4] = venta.getUsuarioid().getNombreUsuario();
            realSubject.addRow(row);
        }
    }

    @Override
    public int getRowCount() {
        return realSubject.getRowCount();
    }

    @Override
    public int getColumnCount() {
        return realSubject.getColumnCount();
    }

    @Override
    public String getColumnName(int i) {
        return realSubject.getColumnName(i);
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return realSubject.getColumnClass(i);
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return realSubject.isCellEditable(i, i1);
    }

    @Override
    public Object getValueAt(int i, int i1) {
        return realSubject.getValueAt(i, i1);
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        realSubject.setValueAt(o, i, i1);
    }

    @Override
    public void addTableModelListener(TableModelListener tl) {
        realSubject.addTableModelListener(tl);
    }

    @Override
    public void removeTableModelListener(TableModelListener tl) {
        realSubject.removeTableModelListener(tl);
    }
}
