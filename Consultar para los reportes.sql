
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


-- REPORTE 2, DADA UNA FECHA INICIO Y FIN SE PUEDE VER UN GRAFICO
select vv.placa,
			 (SELECT count(*)
				from ingreso_salida vis1
				WHERE vis1.placa = vv.placa and (vis1.fecha_hora::timestamp::date) >= ('2014-06-01 17:53:16'::timestamp::date)
																		and (vis1.fecha_hora::timestamp::date) <= ('2014-06-07 17:53:16'::timestamp::date)
																		and vis1.tipo = 'INGRESO') as cant_total

from ingreso_salida vis, vehiculo vv
where vis.placa = vv.placa and (vis.fecha_hora::timestamp::date) >= ('2014-06-01 17:53:16'::timestamp::date)
													 and (vis.fecha_hora::timestamp::date) <= ('2014-06-07 17:53:16'::timestamp::date)
GROUP BY vv.placa
ORDER BY cant_total DESC
LIMIT 10;

-- REPORTE 3, TRANSACCIONES DE UN VEHICULO
SELECT vis.tipo, vis.fecha_hora, vt.descripcion
FROM ingreso_salida vis, tranca vt
WHERE vis.placa = '1234ASD' AND
			vt."id" = vis.id_tranca AND
			(vis.fecha_hora::timestamp::date) >= ('2014-06-01 17:53:16'::timestamp::date) and
			(vis.fecha_hora::timestamp::date) <= ('2014-06-07 17:53:16'::timestamp::date)

ORDER BY vis.fecha_hora ASC;