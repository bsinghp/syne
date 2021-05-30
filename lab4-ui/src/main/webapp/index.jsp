<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
			let intervalHandle = null;
			
			let fetchHistory = () => {
				let url = "/details";
					$.ajax({
						url,
						method: "GET",
						success(response) {
							$("#results").text(response);
							window.clearInterval(intervalHandle);
						}
					});
			};
			
			let init = () => {
				$("#detailsBtn").on("click", () => {
					let url = "/fetchmoviedetails?movie=" + $("#movie").val();
					$.ajax({
						url,
						method: "POST",
						success(response) {
							$("#results").text(response);
							intervalHandle = window.setInterval("fetchHistory()", 1000);
						}
					});
				});
			};
			$(document).ready(init);
		</script>
</head>
<body>
	<input type="text" name="movie" placeholder="Enter your movie name"
		id="movie">
	<br />
	<button id="detailsBtn" type="button">Fetch details</button>
	<h3 id="results"></h3>
	
</body>
</html>