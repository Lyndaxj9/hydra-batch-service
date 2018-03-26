# hydra-batch-service
Fields in the Batch and BatchLocation beans are as follows:
* Batch
* BatchLocation

The controller can perform the following functions:
* BatchController
	* getBatchByName(@PathVariable String name), via a GET to /one/batch/byname/{name}
	* getBatchById(@PathVariable Integer id), via a GET to /one/batch/byid/{id}
	* getAllBatchesOrdered(), via a GET to /all/batch/ordered
	* getAllBatchesMapped(), via a GET to /all/batch/mapped
	* getAllBatchesSet(), via a GET to /all/batch/set
	* addBatch(@RequestBody Batch batch), via a POST to /add/batch
	* updateBatchInfo(@PathVariable Integer id, @RequestBody Batch batch), via a PUT to /update/batch/{id}
	* deleteBatch(@PathVariable Integer id), via a DELETE to /delete/batch/{id}
* BatchLocationController
	* getBatchLocationById(@PathVariable Integer id), via a GET to /one/batchlocation/byid/{id}
