
-- REPORTE 1, DADA UNA FECHA, OBTIENE TODOS LA CANTIDAD DE VEHICULOS QUE INGRESARON Y SALIERON EN TAL FECHA
select vv.placa,
			 (SELECT count(*)
				from ingreso_salida vis1
				WHERE vis1.placa = vv.placa and (vis1.fecha_hora::timestamp::date) = ('2014-05-31 17:53:16'::timestamp::date)
				and vis1.tipo = 'INGRESO') as "cantIngresos", 
				(SELECT count(*)
				from ingreso_salida vis2
				WHERE vis2.placa = vv.placa and (vis2.fecha_hora::timestamp::date) = ('2014-05-31 17:53:16'::timestamp::date)
				and vis2.tipo = 'SALIDA') as "cantSalidas"

from ingreso_salida vis, vehiculo vv
where vis.placa = vv.placa and (vis.fecha_hora::timestamp::date) = ('2014-05-31 17:53:16'::timestamp::date)
GROUP BY vv.placa
ORDER BY vv.placa