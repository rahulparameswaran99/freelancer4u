<script>
	import { isAuthenticated, user } from "../store";
</script>

<h1 class="mt-3">Account Details</h1><br>
<p><img src={$user.picture} alt="test" /></p>

<p><b>Name: </b>{$user.name}</p>
<p><b>Nickname: </b>{$user.nickname}</p>
<p><b>E-Mail: </b> {$user.email}</p>

{#if $user.user_roles && $user.user_roles.length > 0}
 <p><b>Roles:</b> {$user.user_roles}</p>
 {/if} 
