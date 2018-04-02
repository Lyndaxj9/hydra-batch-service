# hydra-batch-service
Fields in the Batch and BatchLocation beans are as follows:
* Batch
	* Integer batchId
	* String batchName
	* Integer batchLocationId
	* Timestamp batchStartDate
	* Timestamp batchEndDate
	* Integer curriculumId
	* HashSet<Integer> associates //is not functional, has no data
* BatchLocation
	* Integer batchLocationId
	* String batchLocationName

The controller can perform the following functions:
* BatchController
	* getBatchByName(@PathVariable String name), via a GET to /one/batch/byname/{name}
	* getBatchById(@PathVariable Integer id), via a GET to /one/batch/byid/{id}
	* getCurriculumIdByBatch(@PathVariable Integer id), via a GET to /one/batch/curriculum/{id}
	* getAllBatchesOrdered(), via a GET to /all/batch/ordered
	* getAllBatchesMapped(), via a GET to /all/batch/mapped
	* getAllBatchesSet(), via a GET to /all/batch/set
	* getAllBatchesByDate(@PathVariable Long startDate, @PathVariable Long endDate), via a GET to /all/batch/btw/{startDate}/{endDate}
	* addBatch(@RequestBody Batch batch), via a POST to /add/batch
	* updateBatchInfo(@PathVariable Integer id, @RequestBody Batch batch), via a PUT to /update/batch/{id}
	* deleteBatch(@PathVariable Integer id), via a DELETE to /delete/batch/{id}
* BatchLocationController
	* getBatchLocationById(@PathVariable Integer id), via a GET to /one/batchlocation/byid/{id}
	* getAllBatchLocation(), via a GET to /all/batchlocation
