<script>
	import { isAuthenticated, user } from "../store";
</script>

<h1 class="mt-3">Account Details</h1><br>
<img src={$user.picture} alt="test" /> <br>

<label class="form-label" for="Name">Name: {$user.name}</label><br>
<label class="form-label" for="Nickname">Nickname: {$user.nickname}</label><br>
<label class="form-label" for="Email">E-Mail: {$user.email}</label>