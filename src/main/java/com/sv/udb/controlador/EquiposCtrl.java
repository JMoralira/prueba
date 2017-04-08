
package com.sv.udb.controlador;

import com.sv.udb.modelo.Equipos;
import com.sv.udb.recursos.Conexion;
import java.awt.Image;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Jose Lira
 */
public class EquiposCtrl {
    
       //GUARDAR
    public boolean guar(Equipos obje)
    {
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("insert into equipos values(NULL,?,?,?)");
            cmd.setString(1, obje.getNombEqui());
            cmd.setString(2, obje.getDescEqui());
            cmd.setBytes(3, obje.getImag());
            cmd.executeUpdate();
            resp=true;
        }
        catch(Exception ex)
        {
            System.err.println("Error al guardar Equipos: " + ex.getMessage());
        }
        finally
        {
            try {
                if(cn != null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } catch (SQLException err) {
                err.printStackTrace();
            }
        }
        return resp;
    }
    
    //MOSTRAR
    public List<Equipos> consTodo()
    {
        List<Equipos> resp = new ArrayList();
        Connection cn = new Conexion().getConn();
        try 
        {
            PreparedStatement cmd = cn.prepareStatement("select * from equipos");
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp.add(new Equipos(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getBytes(4)));               
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        finally
        {
            try {
                if(cn != null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
        return resp;
    }
  
      public Equipos cons(int codi)
    {
        //List<Equipos> resp = new ArrayList();
        Equipos resp= null;
        Connection cn = new Conexion().getConn();
        try 
        {
            
            PreparedStatement cmd = cn.prepareStatement("select * from equipos where codi_equi = ?");
            cmd.setString(1, String.valueOf(codi));
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp = (new Equipos(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getBytes(4)));               
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        finally
        {
            try {
                if(cn != null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
        return resp;
    }
  
    //MODIFICAR
    public boolean modi(Equipos obje)
    {
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("update equipos set nomb_equi = '"+obje.getNombEqui()+"' , desc_equi = '"+obje.getDescEqui()+"' ,imagen = '"+obje.getImag()+"' where codi_equi = "+obje.getCodiEqui()+"");
            cmd.executeUpdate();
            resp=true;
        }
        catch(Exception ex)
        {
            System.err.println("Error al modificar: " + ex.getMessage());
        }
        finally
        {
            try {
                if(cn != null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } catch (SQLException err) {
                err.printStackTrace();
            }
        }
        return resp;
    }
    
    //ELIMINAR
    public boolean elim(Equipos obje)
    {
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("delete from equipos where codi_equi = "+obje.getCodiEqui()+"");
            cmd.executeUpdate();
            resp=true;
        }
        catch(Exception ex)
        {
            System.err.println("Error al Eliminar: " + ex.getMessage());
        }
        finally
        {
            try {
                if(cn != null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } catch (SQLException err) {
                err.printStackTrace();
            }
        }
        return resp;
    }
     public Equipos concmb (int codiEqui)
     {
         Equipos resp = new Equipos();
         Connection cn = new Conexion().getConn();
         try {
              PreparedStatement cmd = cn.prepareStatement("select * from equipos where codi_equi=?");
              cmd.setInt(1, codiEqui);
              ResultSet rs = cmd.executeQuery();
              
              while(rs.next())
              {
                  resp.setCodiEqui(rs.getInt(1));
                  resp.setNombEqui(rs.getString(2));
                  resp.setDescEqui(rs.getString(3));
              }
         } catch (Exception e) {
         }
         return resp;
     }
 
    
}
